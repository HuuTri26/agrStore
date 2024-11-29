package agrStore.database.router;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import agrStore.entity.RoleEntity;

public class DynamicConnectionRouter extends AbstractRoutingDataSource {

	private static final ThreadLocal<String> currentDataSourceKey = new ThreadLocal<String>();

	@Override
	protected Object determineCurrentLookupKey() {
		System.out.println(currentDataSourceKey.get());
		return currentDataSourceKey.get();
	}

	public static void setDataSourceKey(String dataSourcKey) {
		System.out.println(dataSourcKey);
		currentDataSourceKey.set(dataSourcKey);
	}

	public void clearDataSourceKey() {
		currentDataSourceKey.remove();
	}

	public void route(RoleEntity role) {
		// Bản đồ ánh xạ role -> data source key
		Map<String, String> roleToDataSourceMap = Map.of(
				"Admin", "ADMIN_DB", 
				"Employee", "EMPLOYEE_DB", 
				"Customer", "CUSTOMER_DB");

		// Lấy data source key tương ứng với role, nếu không có thì dùng DEFAULT_DB
		String dataSourceKey = roleToDataSourceMap.getOrDefault(role.getName(), "DEFAULT_DB");
		System.out.println(dataSourceKey);
		setDataSourceKey(dataSourceKey);
		//System.out.println(dataSourceKey);
	}
}
