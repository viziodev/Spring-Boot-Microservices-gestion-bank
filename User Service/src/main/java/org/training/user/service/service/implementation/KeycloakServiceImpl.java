package org.training.user.service.service.implementation;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;
import org.training.user.service.config.KeyCloakManager;
import org.training.user.service.service.KeycloakService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class KeycloakServiceImpl implements KeycloakService {

    private final KeyCloakManager keyCloakManager;

    /**
     * Creates a new user in the KeyCloak system.
     *
     * @param  userRepresentation  the user representation object containing the user details
     * @return                     the status code indicating the success or failure of the user creation
     */
    @Override
    public Integer createUser(UserRepresentation userRepresentation) {

        return keyCloakManager.getKeyCloakInstanceWithRealm().users().create(userRepresentation).getStatus();
    }

    /**
     * Retrieves a list of UserRepresentation objects based on the provided email ID.
     *
     * @param  emailId  the email ID of the user
     * @return          a list of UserRepresentation objects
     */
    @Override
    public List<UserRepresentation> readUserByEmail(String emailId) {

        return keyCloakManager.getKeyCloakInstanceWithRealm().users().search(emailId);
    }

    /**
     * Retrieves a list of user representations based on the provided authentication IDs.
     *
     * @param  authIds  a list of authentication IDs
     * @return          a list of user representations
     */
    @Override
    public List<UserRepresentation> readUsers(List<String> authIds) {

        return authIds.stream().map(authId -> {
            UserResource usersResource = keyCloakManager.getKeyCloakInstanceWithRealm().users().get(authId);
            return usersResource.toRepresentation();
        }).collect(Collectors.toList());
    }

    /**
     * Retrieves a user representation based on the provided authentication ID.
     *
     * @param  authId  the authentication ID of the user
     * @return         the user representation object
     */
    @Override
    public UserRepresentation readUser(String authId) {

        UsersResource userResource = keyCloakManager.getKeyCloakInstanceWithRealm().users();

        return userResource.get(authId).toRepresentation();
    }

    /**
     * Updates the user with the provided user representation.
     *
     * @param  userRepresentation  the user representation to update the user with
     */
    @Override
    public void updateUser(UserRepresentation userRepresentation) {

        keyCloakManager.getKeyCloakInstanceWithRealm().users()
                .get(userRepresentation.getId()).update(userRepresentation);
    }
}
