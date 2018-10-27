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

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import io.qfast.vaadin.ui.VerticalLayout.VerticalSpacedLayout;

import java.io.Serializable;
import java.util.Locale;

import static com.vaadin.shared.ui.ContentMode.HTML;
import static com.vaadin.ui.Alignment.BOTTOM_LEFT;
import static com.vaadin.ui.Alignment.BOTTOM_RIGHT;

/**
 * @author Mawaziny
 */
public class ConfirmDialog extends Window {

    private static final long serialVersionUID = 3291658480588693778L;

    private final Button confirm;
    private final Button cancel;
    private boolean confirmed;

    public ConfirmDialog(String title, String message, String confirmCaption, String cancelCaption, Locale locale, Listener listener) {
        this(title, message, confirmCaption, cancelCaption, locale);
        addListener(listener);
    }

    public ConfirmDialog(String title, String messageText, String confirmCaption, String cancelCaption, Locale locale) {
        setClosable(false);
        setDraggable(false);
        setResizable(false);
        addStyleName("dialog");
        setModal(true);
        setCaption(title);
        setWidth(300, Unit.PIXELS);
        setHeight(225, Unit.PIXELS);

        confirm = new PrimaryButton(confirmCaption);
        cancel = new Button(cancelCaption);

        VerticalLayout content = new VerticalSpacedLayout().withSizeFull();

        messageText = messageText.replaceAll("\\r?\\n", "<br />");
        Label message = new Label(messageText, HTML);
        content.addComponent(message);

        HorizontalLayout buttons = new HorizontalLayout(confirm, cancel).withSpacing();
        if (locale.getLanguage().equalsIgnoreCase("ar")) {
            content.addComponent(buttons, BOTTOM_LEFT);
        } else {
            content.addComponent(buttons, BOTTOM_RIGHT);
        }

        setContent(content);
    }

    public static void show(Listener listener) {
        ConfirmDialog dialog = new ConfirmDialog("Please confirm...", "Are you sure ?",
                "Confirmed", "Cancel", Locale.ENGLISH, listener);
        UI.getCurrent().addWindow(dialog);
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    private void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    private void addListener(final Listener listener) {
        ClickListener clickListener = e -> {
            if (isEnabled()) {
                setConfirmed(e.getButton() == confirm);
                listener.onClose(this);
                close();
            }
        };
        confirm.addClickListener(clickListener);
        cancel.addClickListener(clickListener);
    }

    public interface Listener extends Serializable {
        void onClose(ConfirmDialog dialog);
    }
}