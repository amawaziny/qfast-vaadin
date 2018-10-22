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

package io.qfast.vaadin.ui;

import com.vaadin.server.StreamResource.StreamSource;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.awt.image.BufferedImage.TYPE_INT_RGB;

/**
 * @author Mawaziny
 */
public class ProfileImage implements StreamSource {

    private static final long serialVersionUID = -1864229613286104224L;

    private final String data;
    private final boolean base64;

    public ProfileImage(String data, boolean base64) {
        this.data = data;
        this.base64 = base64;
    }

    public InputStream getStream() {
        if (data != null) {
            if (base64) {
                String base64Image = data.split(",")[1];
                byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);

                return new ByteArrayInputStream(imageBytes);
            } else {
                BufferedImage image = new BufferedImage(80, 75, TYPE_INT_RGB);

                Graphics drawable = image.getGraphics();
                drawable.setColor(Color.decode("#1780F2"));
                drawable.fillRect(0, 0, 80, 75);
                drawable.setColor(Color.decode("#F5B00D"));

                Font font = new Font(drawable.getFont().getName(), Font.PLAIN, 21);
                drawable.setFont(font);
                drawable.drawString(data, data.length() == 1 ? 35 : 27, 45);

                drawable.dispose();

                ByteArrayOutputStream imageBuffer = new ByteArrayOutputStream();
                try {
                    ImageIO.write(image, "png", imageBuffer);
                } catch (IOException ignore) {
                }
                return new ByteArrayInputStream(imageBuffer.toByteArray());
            }
        }
        return null;
    }
}
