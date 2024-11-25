package agrStore.service;

import java.util.List;

import agrStore.entity.ImportBillEntity;

public interface ImportBillService {
	
	public List<ImportBillEntity> getAllImportBill();
	public ImportBillEntity getImportBillEntityById(Integer id);

}
