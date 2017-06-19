package com.joaquinalan.steeringwheelbluetooth.model.bluetooth.bluetoothconnection;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

/**
 * Created by joaquinalan on 20/05/2017.
 */

public class BluetoothService implements BluetoothConnection {
    private static final String TAG = "BluetoothService";
    //private final BluetoothAdapter mBluetoothAdapter;
    private Handler mHandler; // handler that gets info from Bluetooth service
    //private ConnectThread mConnectThread;
    private BluetoothClient mBluetoothClient;
    //private AcceptThread mAcceptThread;
    private BluetoothServer mBluetoothServer;
    private BluetoothWorking mBluetoothWorking;
    private BluetoothServiceListener mBluetoothServiceListener;

    public BluetoothService(Handler handler) {
        //  mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        mHandler = handler;
        setup();
    }

    public BluetoothService(BluetoothServiceListener bluetoothServiceListener) {
        mBluetoothServiceListener = bluetoothServiceListener;
        //  mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        setup();
    }

    public void write(byte[] out) {
        Log.d(TAG, "write: Write Called.");
        // Perform the write unsynchronized
        mBluetoothWorking.write(out);
    }

    /**
     * AcceptThread starts and sits waiting for a connection.
     * Then ConnectThread starts and attempts to make a connection with the other devices AcceptThread.
     **/

    public void startClientConnection(BluetoothDevice hostDevice) {
        Log.d(TAG, "startClientConnection: Started.");

        mBluetoothClient = new BluetoothClient(this, hostDevice);
        mBluetoothClient.start();
    }

    /**
     * Start the chat service. Specifically setup AcceptThread to begin a
     * session in listening (server) mode. Called by the Activity onResume()
     */
    private synchronized void setup() {
        Log.d(TAG, "setup");

        // Cancel any thread attempting to make a connection
        if (mBluetoothClient != null) {
            mBluetoothClient.cancel();
            mBluetoothClient = null;
        }

        // Cancel any thread currently running a connection
        if (mBluetoothWorking != null) {
            mBluetoothWorking.cancel();
            mBluetoothWorking = null;
        }

        // Start the thread to listen on a BluetoothServerSocket
        if (mBluetoothServer == null) {
            mBluetoothServer = new BluetoothServer(this);
            mBluetoothServer.start();
        }
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        Log.d(TAG, "stop");

        if (mBluetoothClient != null) {
            mBluetoothClient.cancel();
            mBluetoothClient = null;
        }

        if (mBluetoothWorking != null) {
            mBluetoothWorking.cancel();
            mBluetoothWorking = null;
        }

        if (mBluetoothServer != null) {
            mBluetoothServer.cancel();
            mBluetoothServer = null;
        }
    }

    @Override
    public void onConnectedSocket(BluetoothSocket bluetoothSocket) {
        Log.d(TAG, "connected: Starting.");
        // Cancel the thread that completed the connection
//        if (mConnectThread != null) {
//            mConnectThread.cancel();
//            mConnectThread = null;
//        }

        // Cancel any thread currently running a connection
        if (mBluetoothWorking != null) {
            mBluetoothWorking.cancel();
            mBluetoothWorking = null;
        }

        // Cancel the accept thread because we only want to connect to one device
        if (mBluetoothServer == null) {
            mBluetoothServer = new BluetoothServer(this);
            mBluetoothServer.start();
        }

        //mBluetoothWorking = new BluetoothWorking(bluetoothSocket, mHandler);
        mBluetoothWorking = new BluetoothWorking(bluetoothSocket);
        mBluetoothWorking.start();
        mBluetoothServiceListener.onConnectedSocket();
    }

    @Override
    public void onConnectionFailed() {
        mBluetoothServiceListener.onConnectionFailed();
    }

//    private class AcceptThread extends Thread {
//        private final BluetoothServerSocket mmServerSocket;
//
//        public AcceptThread() {
//            // Use a temporary object that is later assigned to mmServerSocket
//            // because mmServerSocket is final.
//            BluetoothServerSocket tmp = null;
//            try {
//                // MY_UUID is the app's UUID string, also used by the client code.
//                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord
//                        (BluetoothConstants.SERVICE_NAME, BluetoothConstants.MY_UUID);
//            } catch (IOException e) {
//                Log.e(TAG, "Socket's listen() method failed", e);
//            }
//            mmServerSocket = tmp;
//        }
//
//        public void run() {
//            BluetoothSocket socket = null;
//            // Keep listening until exception occurs or a socket is returned.
//            while (true) {
//                try {
//                    socket = mmServerSocket.accept();
//                } catch (IOException e) {
//                    Log.e(TAG, "Socket's accept() method failed", e);
//                    break;
//                }
//
//                if (socket != null) {
//                    // A connection was accepted. Perform work associated with
//                    // the connection in a separate thread.
//                    onConnectedSocket(socket);
//                    try {
//                        mmServerSocket.close();
//                    } catch (IOException e) {
//                        Log.e(TAG, "Could not close unwanted socket", e);
//                    }
//                    break;
//                }
//            }
//        }
//
//        // Closes the connect socket and causes the thread to finish.
//        void cancel() {
//            try {
//                mmServerSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Could not close the connect socket", e);
//            }
//        }
//    }

//    private class ConnectThread extends Thread {
//        private final BluetoothSocket mmSocket;
//        private final BluetoothDevice mmDevice;
//
//        public ConnectThread(BluetoothDevice device) {
//            // Use a temporary object that is later assigned to mmSocket
//            // because mmSocket is final.
//            BluetoothSocket tmp = null;
//            mmDevice = device;
//
//            try {
//                // Get a BluetoothSocket to connect with the given BluetoothDevice.
//                // MY_UUID is the app's UUID string, also used in the server code.
//                tmp = device.createRfcommSocketToServiceRecord(BluetoothConstants.MY_UUID);
//            } catch (IOException e) {
//                Log.e(TAG, "Socket's create() method failed", e);
//            }
//            mmSocket = tmp;
//        }
//
//        public void run() {
//            // Cancel discovery because it otherwise slows down the connection.
//            mBluetoothAdapter.cancelDiscovery();
//
//            try {
//                // Connect to the remote device through the socket. This call blocks
//                // until it succeeds or throws an exception.
//                mmSocket.connect();
//                Log.e(TAG, "Si se pudo man");
//            } catch (IOException connectException) {
//                // Unable to connect; close the socket and return.
//                Log.e(TAG, "Te apendejaste wey");
//                try {
//                    mmSocket.close();
//                    Log.d(TAG, "run: Closed Socket.");
//                } catch (IOException closeException) {
//                    Log.e(TAG, "Could not close the client socket", closeException);
//                }
//                return;
//            }
//
//            // The connection attempt succeeded. Perform work associated with
//            // the connection in a separate thread.
//            onConnectedSocket(mmSocket);
//        }
//
//        // Closes the client socket and causes the thread to finish.
//        public void cancel() {
//            try {
//                mmSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Could not close the client socket", e);
//            }
//        }
//    }

//    private class ConnectedThread extends Thread {
//        private final BluetoothSocket mmSocket;
//        private final InputStream mmInStream;
//        private final OutputStream mmOutStream;
//        private byte[] mmBuffer; // mmBuffer store for the stream
//
//        public ConnectedThread(BluetoothSocket socket) {
//            mmSocket = socket;
//            InputStream tmpIn = null;
//            OutputStream tmpOut = null;
//
//            // Get the input and output streams; using temp objects because
//            // member streams are final.
//            try {
//                tmpIn = socket.getInputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating input stream", e);
//            }
//            try {
//                tmpOut = socket.getOutputStream();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when creating output stream", e);
//            }
//
//            mmInStream = tmpIn;
//            mmOutStream = tmpOut;
//        }
//
//        public void run() {
//            mmBuffer = new byte[4];
//            int numBytes; // bytes returned from read()
//
//            // Keep listening to the InputStream until an exception occurs.
//            while (true) {
//                try {
//                    //Read from the InputStream.
//
//                    numBytes = mmInStream.read(mmBuffer);
//
//                    Message readMsg = mHandler.obtainMessage(
//                            BluetoothConstants.MESSAGE_READ, numBytes, -1,
//                            mmBuffer);
//                    readMsg.sendToTarget();
//
//                    String readMessage = new String(mmBuffer, 0, numBytes);
//                    Log.d(TAG, readMessage);
//                    // Send the obtained bytes to the UI activity.
//                    //Log.d(TAG, "¿Qué pedo está pasando here?");
//
//                } catch (IOException e) {
//                    Log.d(TAG, "Input stream was disconnected", e);
//                    break;
//                }
//            }
//        }
//
//        // Call this from the main activity to send data to the remote device.
//        public void write(byte[] bytes) {
//            try {
//                mmOutStream.write(bytes);
//                Log.e(TAG, "Si se pudo escribir bato");
//
//                // Share the sent message with the UI activity.
////                Message writtenMsg = mHandler.obtainMessage(
////                        BluetoothConstants.MESSAGE_WRITE, -1, -1, mmBuffer);
////                writtenMsg.sendToTarget();
//            } catch (IOException e) {
//                Log.e(TAG, "Error occurred when sending data", e);
//
//                // Send a failure message back to the activity.
////                Message writeErrorMsg =
////                        mHandler.obtainMessage(BluetoothConstants.MESSAGE_TOAST);
////                Bundle bundle = new Bundle();
////                bundle.putString("toast",
////                        "Couldn't send data to the other device");
////                writeErrorMsg.setData(bundle);
////                mHandler.sendMessage(writeErrorMsg);
//            }
//        }
//
//        // Call this method from the main activity to shut down the connection.
//        public void cancel() {
//            try {
//                mmSocket.close();
//            } catch (IOException e) {
//                Log.e(TAG, "Could not close the connect socket", e);
//            }
//        }
//    }
}
