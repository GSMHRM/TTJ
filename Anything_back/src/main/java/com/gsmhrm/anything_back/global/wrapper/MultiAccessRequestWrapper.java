package com.gsmhrm.anything_back.global.wrapper;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class MultiAccessRequestWrapper extends HttpServletRequestWrapper {
    private final ByteArrayOutputStream contents = new ByteArrayOutputStream(); // request content를 여기에 저장한다. (캐싱)
    private ByteArrayInputStream inputStream;

    public MultiAccessRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    // 이 메소드를 통해서 request body의 내용을 inputStream으로 읽는다.
    @Override
    public ServletInputStream getInputStream() throws IOException, IOException {
        IOUtils.copy(super.getInputStream(), contents); // request content를 복사

        // read를 호출하면 buffer(저장된 내용)을 보내주는 커스텀 ServletInputStream 객체를 생성해서 반환
        return new ServletInputStream() {
            private final ByteArrayInputStream buffer = new ByteArrayInputStream(contents.toByteArray());

            @Override
            public int read() throws IOException {
                return buffer.read();
            }

            @Override
            public boolean isFinished() {
                return buffer.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new RuntimeException("Not implemented");
            }
        };
    }

    // contents를 byteArray로 반환
    public byte[] getContents() {
        return this.inputStream.readAllBytes();
    }
}

