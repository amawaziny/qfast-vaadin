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

import com.vaadin.server.StreamResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.VerticalLayout;
import io.qfast.vaadin.ui.ProfileImage;

import java.io.InputStream;

import static com.vaadin.ui.Alignment.MIDDLE_LEFT;
import static com.vaadin.ui.Notification.Type.ERROR_MESSAGE;

/**
 * @author Mawaziny
 */
public class UploadImage extends VerticalLayout implements FinishedListener {

    private static final long serialVersionUID = -282814550958749763L;

    private final FileBufferReceiver receiver;
    private final Upload upload;
    private final Image image;
    private final Label descriptionLbl;
    private String imageHeight;
    private String imageWidth;

    public UploadImage() {
        addStyleName("upload-image");
        receiver = new FileBufferReceiver();
        image = new Image();
        descriptionLbl = new Label();
        upload = new Upload(null, receiver);
        upload.setImmediateMode(true);
        upload.addFinishedListener(this);
        upload.setButtonCaption("Upload an image");

        HorizontalLayout uploadLayout = new HorizontalLayout();
        uploadLayout.setSpacing(true);

        Image identityImage = new Image(null, new ThemeResource("img/identity.png"));
        uploadLayout.addComponent(identityImage);
        uploadLayout.setComponentAlignment(identityImage, MIDDLE_LEFT);

        VerticalLayout uploadControlLayout = new VerticalLayout();
        uploadLayout.addComponent(uploadControlLayout);

        uploadControlLayout.addComponent(upload);
        uploadControlLayout.addComponent(descriptionLbl);

        addComponent(image);
        addComponent(uploadLayout);
    }

    @Override
    public void setWidth(String width) {
        super.setWidth(width);
        imageWidth = width;
    }

    @Override
    public void setHeight(String height) {
        super.setHeight(height);
        imageHeight = height;
    }

    public void setButtonCaption(String caption) {
        upload.setButtonCaption(caption);
    }

    public void setDescriptionCaption(String caption) {
        descriptionLbl.setValue(caption);
    }

    public InputStream getContentAsStream() {
        return receiver.getContentAsStream();
    }

    public String getContentAsBase64() {
        return receiver.getContentAsBase64();
    }

    public String getMimeType() {
        return receiver.getMimeType();
    }

    public String getExtension() {
        return receiver.getExtension();
    }

    public String getFileName() {
        return receiver.getFileName();
    }

    public long getFileSize() {
        return receiver.getFileSize();
    }

    public boolean isEmpty() {
        return receiver.isEmpty();
    }

    @Override
    public void uploadFinished(FinishedEvent event) {
        if (!receiver.isEmpty()) {
            if (receiver.getMimeType().startsWith("image")) updateDisplay();
            else {
                clear();
                Notification.show("Please choose an image", ERROR_MESSAGE);
            }
        }
    }

    protected void updateDisplay() {
        String fileName = String.valueOf(System.currentTimeMillis()) + receiver.getExtension();
        StreamResource resource = new StreamResource(receiver::getContentAsStream, fileName);
        resource.setCacheTime(0);
        image.setSource(resource);
        image.setWidth(imageWidth);
        image.setHeight(imageHeight);
    }

    public void clear() {
        receiver.clear();
        image.setSource(null);
    }

    public void setImageSource(String imageSource) {
        if (imageSource != null) {
            image.setSource(new StreamResource(() -> new ProfileImage(imageSource).getStream(),
                    "profile_image" + receiver.getExtension(imageSource)));
            image.setWidth(imageWidth);
            image.setHeight(imageHeight);
        }
    }
}