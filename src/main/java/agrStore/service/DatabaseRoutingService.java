package agrStore.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.RoleEntity;

@Service
@Transactional
public interface DatabaseRoutingService {
	public void routingUserWithRole(RoleEntity role);
}
