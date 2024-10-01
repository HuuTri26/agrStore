package agrStore.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.RoleDAO;
import agrStore.entity.RoleEntity;
import agrStore.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	RoleDAO RoleDAO;

	@Override
	public void addRole(RoleEntity role) {
		RoleDAO.addRole(role);
		
	}

	@Override
	public void updateRole(RoleEntity role) {
		RoleDAO.updateRole(role);
		
	}

	@Override
	public RoleEntity getRoleById(Integer id) {
		return RoleDAO.getRoleById(id);
	}
}
