package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileHandlerServiceTest {

    @Mock
    private ProfileValidator profileValidatorMock;

    @Mock
    private ProfileRepository profileRepositoryMock;

    @InjectMocks
    private ProfileHandlerService underTest;

    @Test
    public void testSaveMethodWhenInputIsASingleProfile() {
        when(profileRepositoryMock.saveAll(any())).thenReturn(any());
        List<ProfileEntity> entities = List.of(new ProfileEntity());
        assertDoesNotThrow(() -> underTest.saveAll(entities));
        verify(profileValidatorMock).validate(any());
        verify(profileRepositoryMock).saveAll(any());
    }

    @Test
    public void testSaveMethodWhenInputIsNotValidAndValidatorThrowsAnException() {
        doThrow(InvalidProfileException.class).when(profileValidatorMock).validate(any());
        List<ProfileEntity> entities = List.of(new ProfileEntity());
        assertThrows(InvalidProfileException.class, () -> underTest.saveAll(entities));
        verify(profileValidatorMock).validate(any());
        verifyZeroInteractions(profileRepositoryMock);
    }

    @Test
    public void testRetrieveMethod() {
        ProfileEntity entity = new ProfileEntity();
        entity.setName("entity-name");
        entity.setId(42L);
        List<ProfileEntity> entities = List.of(entity);
        when(profileRepositoryMock.findAll()).thenReturn(entities);
        List<ProfileEntity> result = underTest.findAll();
        assertNotNull(result);
        assertEquals(entities.size(), result.size());
        assertEquals("entity-name", result.get(0).getName());
        assertEquals(42L, result.get(0).getId().longValue());
        verify(profileRepositoryMock).findAll();
    }

}