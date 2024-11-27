package agrStore.DAO;

import java.util.List;

import agrStore.entity.ImportBillEntity;

public interface ImportBillDAO {
	
	List<ImportBillEntity> getAllImportBill();
	ImportBillEntity getImportBillById(Integer id);

}
