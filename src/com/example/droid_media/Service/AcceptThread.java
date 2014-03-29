package com.example.droid_media.Service;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Kevin Shi on 2014-03-28.
 */
public class AcceptThread extends Thread{
    //use this for debugging
    private static final String TAG = "Accept Thread:";
    private static final boolean TRUE = true;
    private ArrayList<UUID> mUuids;
    private ArrayList<BluetoothSocket> mSockets;
    private ArrayList<String> mDeviceAddresses;


    private static final String NAME = "DroidMedia";
    private BluetoothAdapter mAdapter;

    BluetoothServerSocket serverSocket = null;

    public AcceptThread(BluetoothAdapter mAdapter,ArrayList<UUID> mUuids,ArrayList<BluetoothSocket> mSockets,
                        ArrayList<String> mDeviceAddresses){
        this.mAdapter = mAdapter;
        this.mUuids= mUuids;
        this.mSockets=mSockets;
        this.mDeviceAddresses = mDeviceAddresses;
    }

    public void run() {
        if (TRUE) Log.d (TAG, "Begin mAcceptThread" + this);
        setName("AcceptThread");
        BluetoothSocket socket = null;
        try {
            for (int i = 0; i<3;i++) {
                serverSocket = mAdapter.listenUsingRfcommWithServiceRecord(NAME, mUuids.get(i));
                socket = serverSocket.accept();
                if (socket!=null) {
                    String address = socket.getRemoteDevice().getAddress();
                    mSockets.add(socket);
                    mDeviceAddresses.add(address);
                    connected (socket,socket.getRemoteDevice()); //implement connected
                }
            }
        }

        catch (IOException e) {
            Log.d(TAG, "accept() failed", e);
        }
        if (TRUE) Log.i(TAG, "END mAcceptThread");
    }
    public void cancel () {
        if (TRUE) Log.d(TAG, "cancel" + this);
        try {
            serverSocket
        }
    }
}
