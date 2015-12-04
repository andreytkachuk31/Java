package com.epam.kation.model.event;

import java.util.Date;

public interface Event {

	Long getId();

	void setId(Long id);

	String getTitle();

	void setTitle(String title);

	Date getDate();

	void setDate(Date date);
}
