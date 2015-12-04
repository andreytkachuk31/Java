package com.epam.kation.model.ticket;

public interface Ticket {

	public enum Category {
		STANDARD, PREMIUM, BAR
	}

	Long getId();

	void setId(Long id);

	Long getEventId();

	void setEventId(Long eventId);

	Long getUserId();

	void setUserId(Long userId);

	Category getCategory();

	void setCategory(Category category);

	Integer getPlace();

	void setPlace(Integer place);
}
