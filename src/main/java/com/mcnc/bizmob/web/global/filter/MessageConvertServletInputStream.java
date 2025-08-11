package com.mcnc.bizmob.web.global.filter;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;

public class MessageConvertServletInputStream extends ServletInputStream {

	private final InputStream inputStream;
	
	public MessageConvertServletInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	@Override
	public boolean isFinished() {
		try {
			return inputStream.available() == 0;
        } catch (IOException e) {
        	return true;
	    }
	}

	@Override
	public boolean isReady() {
		return true;
	}

	@Override
	public void setReadListener(ReadListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int read() throws IOException {
		return inputStream.read();
	}

}
