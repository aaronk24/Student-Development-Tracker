package org.tg8.sdt;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.tg8.sdt.data.SDTDAO;

class Cleanup extends WindowAdapter implements InvocationHandler {
	
	private SDTDAO dao;
	
	Cleanup (SDTDAO dao) {
		this.dao = dao;
	}

	@Override
	/**
	 * Safely destroys the DAO
	 */
	public void windowClosing(WindowEvent we) {
		this.dao.destroy();
		we.getWindow().dispose();
	}
	
	// InvocationHandler implementation
    // Called every time an ApplicationListener method is invoked
    public Object invoke (Object proxy, Method method, Object[] args) throws Throwable {
        
    	if ("handleQuit".equals(method.getName())) {
    		this.userQuitMACOSX();
    		Method setHandledMethod = args[0].getClass().getDeclaredMethod("setHandled", new Class[] { boolean.class });
            // If the target method returns a boolean, use that as a hint
            setHandledMethod.invoke(args[0], new Object[] {Boolean.TRUE});
    	}
    	
    	
        // All of the ApplicationListener methods are void; return null regardless of what happens
        return null;
    }
	
	/**
	 * 
	 */
	public void userQuitMACOSX() {
		this.dao.destroy();
	}
	
}
