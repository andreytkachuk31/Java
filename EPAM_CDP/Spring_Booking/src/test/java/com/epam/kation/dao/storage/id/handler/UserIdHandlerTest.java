package com.epam.kation.dao.storage.id.handler;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.kation.model.user.User;

@RunWith(MockitoJUnitRunner.class)
public class UserIdHandlerTest extends AbstractHandlerTest {

	@Mock
	private User user;
	
	private IdHandler idHandler = new UserIdHandler();

	@Override
	protected Object getType() {
		return user;
	}

	@Override
	protected void setUpId(Long id) {
		when(user.getId()).thenReturn(id);
	}

	@Override
	protected void verifySetId(Long id) {
		verify(user).setId(ID);
	}

	@Override
	protected IdHandler getIdHandler() {
		return idHandler;
	}
}