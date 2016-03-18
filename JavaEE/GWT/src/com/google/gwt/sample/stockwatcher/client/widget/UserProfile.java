package com.google.gwt.sample.stockwatcher.client.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;

public class UserProfile extends Composite {

    interface UserProfileUiBinder extends UiBinder<FlowPanel, UserProfile> {};

    private static UserProfileUiBinder uiBinder = GWT.create(UserProfileUiBinder.class);

    @UiField
    Label name;
    @UiField
    Label mobile;
    @UiField
    Label email;
    @UiField
    Label web;
    @UiField
    Label bio;

    public UserProfile() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
        name.setText("Andrey");
        email.setText("email");
        mobile.setText("mobile");
        web.setText("web");
        bio.setText("Biography");
    }
}
