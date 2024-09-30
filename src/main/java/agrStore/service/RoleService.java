package agrStore.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import agrStore.entity.RoleEntity;

@Service
@Transactional
public interface RoleService {
	public void addRole(RoleEntity role);
	public void updateRole(RoleEntity role);
	public RoleEntity getRoleById(Integer id);
}
