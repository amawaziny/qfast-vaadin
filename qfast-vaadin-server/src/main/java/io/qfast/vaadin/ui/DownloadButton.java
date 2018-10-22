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

import com.vaadin.server.FileDownloader;
import com.vaadin.server.StreamResource;

import java.io.ByteArrayInputStream;

import static com.vaadin.server.StreamResource.StreamSource;
import static com.vaadin.ui.themes.ValoTheme.BUTTON_LINK;
import static javax.xml.bind.DatatypeConverter.parseBase64Binary;

/**
 * @author Mawaziny
 */
public class DownloadButton extends Button {

    private static final long serialVersionUID = -3649430120528454800L;

    public void setSource(StreamSource resource) {
        new FileDownloader(new StreamResource(resource, getCaption())).extend(this);
    }

    public void setSource(String fileContents) {
        setSource(() -> new ByteArrayInputStream(parseBase64Binary((fileContents.contains(",") ?
                fileContents.split(",")[1] : fileContents))));
    }

    public static class DownloadLink extends DownloadButton {

        private static final long serialVersionUID = -5963548325175078841L;

        public DownloadLink(String caption) {
            setCaption(caption);
            addStyleName(BUTTON_LINK);
        }
    }
}
