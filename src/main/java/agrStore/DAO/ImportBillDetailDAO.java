package agrStore.DAO;

import java.util.List;

import agrStore.entity.ImportBillDetailEntity;

public interface ImportBillDetailDAO {
	
	List<ImportBillDetailEntity> getImportBillDetailByImportBillId(Integer importBillID);

}
