package com.dspread.demoui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;



public class BlueToothConnection {
	private static BluetoothAdapter mAdapter = null;

	/**
	 * 判断是否打开蓝牙设备
	 * @return
	 */
	public boolean open(){
		mAdapter = BluetoothAdapter.getDefaultAdapter();
		while(!mAdapter.isEnabled()){//蓝牙的endable
			mAdapter.enable();
		}
		if(!mAdapter.isEnabled()){
    		return false;
    	}
		return true;
    }
	
	/**
	 * 获取蓝牙设备的地址值
	 * @return
	 */
	private String getBlueToothAddress(){
		Set<BluetoothDevice> s = mAdapter.getBondedDevices();
    	if (s.size()==0) {
    		return "";
    	}
    	Iterator<BluetoothDevice> i = s.iterator();
        while(i.hasNext()){//遍历
        	BluetoothDevice bt = i.next();
        	String bt_name = bt.getName(); 
        	TRACE.d("BT connecting to bt_name: "+bt_name);
        	if(bt_name.startsWith("dspr") || bt_name.startsWith("QPOS") ){//obd linvor QPOS0200101258
        		TRACE.d("BT connecting to: "+(bt.getAddress()));
        		return bt.getAddress();
        	}
        }
        return "";
	}
	
	/**
	 * 获取蓝牙的列表
	 */
	public ArrayList<String> getBluetoothList(){
		ArrayList<String> list = new  ArrayList<String>();
		String btInfo = "";
		Set<BluetoothDevice> s = mAdapter.getBondedDevices();
    	if (s.size()==0) {
    		return list;
    	}
    	Iterator<BluetoothDevice> i = s.iterator();
    	int j = 0;
        while(i.hasNext()){//遍历
        	BluetoothDevice bt = i.next();
        	String bt_name = bt.getName(); 
        	TRACE.d("BT connecting to bt_name: "+bt_name);
//        	if(bt_name.startsWith("dspr") || bt_name.startsWith("QPOS") ){//obd linvor QPOS0200101258
        		TRACE.d("BT connecting to: "+(bt.getAddress()));
        		btInfo = bt_name + "," + bt.getAddress();
        		list.add(j, btInfo);
        		j++;
//        	}
        }
        return list;
	}
	
	public void close(){
		mAdapter.cancelDiscovery();
	}
}
