package agrStore.database.router;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import agrStore.entity.RoleEntity;

public class DynamicConnectionRouter extends AbstractRoutingDataSource {
	// Make this static and ensure thread safety
	private static final ThreadLocal<String> currentDataSourceKey = ThreadLocal.withInitial(() -> "defaultDataSource");

	@Override
	protected Object determineCurrentLookupKey() {
		String key = currentDataSourceKey.get();
		System.out.println("==> Retrieving DataSource Key: " + key);
		return key;
	}

	public void route(RoleEntity role) {
		if (role == null) {
			throw new IllegalArgumentException("==> Role cannot be null");
		}

		Map<String, String> roleToDataSourceMap = Map.of("Admin", "ADMIN_DB", "Employee", "EMPLOYEE_DB", "Customer",
				"CUSTOMER_DB");

		String dataSourceKey = roleToDataSourceMap.get(role.getName());
		if (dataSourceKey == null) {
			throw new IllegalArgumentException("==> No data source found for role: " + role.getName());
		}

		currentDataSourceKey.set(dataSourceKey);
	}

	// Add a method to clear the data source key after request
	public void clearDataSourceKey() {
		System.out.println("==> Clearing DataSource Key");
		currentDataSourceKey.remove();
	}
}
