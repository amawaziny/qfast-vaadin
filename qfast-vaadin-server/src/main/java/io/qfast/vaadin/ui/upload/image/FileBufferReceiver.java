/*
 * Copyright 2018 QFast
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.qfast.vaadin.ui.upload.image;

import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Upload.Receiver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;

import static java.util.Base64.getEncoder;

/**
 * @author Mawaziny
 */
public class FileBufferReceiver implements Receiver {

    private static final long serialVersionUID = 2327980285122681887L;

    private String mimeType;
    private String fileName;
    private File file;

    @Override
    public OutputStream receiveUpload(String fileName, String mimeType) {
        this.fileName = fileName;
        this.mimeType = mimeType;
        try {
            if (file == null) {
                String tmpFileName = VaadinSession.getCurrent().getSession().getId() + "-" +
                        String.valueOf(System.currentTimeMillis());
                file = File.createTempFile(tmpFileName, ".tmp");
                file.deleteOnExit();
            }
            return new FileOutputStream(file);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    InputStream getContentAsStream() {
        try {
            return new FileInputStream(file);
        } catch (final FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void clear() {
        if (file != null && file.exists())
            file.delete();
        fileName = null;
        mimeType = null;
        file = null;
    }

    boolean isEmpty() {
        return file == null || !file.exists();
    }

    long getFileSize() {
        return file.length();
    }

    String getMimeType() {
        try {
            return Files.probeContentType(file.toPath());
        } catch (IOException e) {
            return mimeType;
        }
    }

    public String getFileName() {
        return fileName;
    }

    File getFile() {
        return file;
    }

    public String getContentAsBase64() {
        if (!isEmpty()) {
            try {
                return "data:" + getMimeType() + ";base64," +
                        new String(getEncoder().encode(Files.readAllBytes(file.toPath())));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    String getExtension() {
        return getExtensionByMimeType(mimeType);
    }

    private String getExtensionByMimeType(String mimeType) {
        String[] contentTypeSplit = mimeType.split("/");
        String ext = contentTypeSplit[1];
        if (ext.contains("+"))
            ext = ext.split("\\+")[0];
        return "." + ext;
    }

    String getExtension(String imgBase64) {
        String mineType = imgBase64.substring(imgBase64.indexOf(":") + 1, imgBase64.indexOf(";"));
        return getExtensionByMimeType(mineType);
    }
}