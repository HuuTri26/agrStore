package agrStore.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.database.router.DynamicConnectionRouter;
import agrStore.entity.RoleEntity;
import agrStore.service.DatabaseRoutingService;

@Service
@Transactional
public class DatabaseRoutingServiceImpl implements DatabaseRoutingService {

	@Autowired
	DynamicConnectionRouter connRouter;

	@Override
	public void routingUserWithRole(RoleEntity role) {

		if (role == null || role.getName() == null) {
			throw new IllegalArgumentException("Role or role name cannot be null");
		}
		connRouter.route(role);
	}

	@Override
	public void clearDataSourceKey() {
		connRouter.clearDataSourceKey();
		
	}
	

}
