package agrStore.DAO;

import agrStore.entity.RoleEntity;

public interface RoleDAO {
	public void addRole(RoleEntity role);
	public void updateRole(RoleEntity role);
	public RoleEntity getRoleById(Integer id);

}
