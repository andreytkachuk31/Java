package com.epam.kation.model.ticket;

public class DefaultTicket implements Ticket {

	private Long id;
	private Long eventId;
	private Long userId;
	private Ticket.Category category;
	private Integer place;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Long getEventId() {
		return eventId;
	}

	@Override
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	@Override
	public Long getUserId() {
		return userId;
	}

	@Override
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Override
	public Category getCategory() {
		return category;
	}

	@Override
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public Integer getPlace() {
		return place;
	}

	@Override
	public void setPlace(Integer place) {
		this.place = place;
	}
}
