package agrStore.service;

import java.util.List;

import agrStore.entity.ImportBillDetailEntity;

public interface ImportBillDetailService {
	
	public List<ImportBillDetailEntity> getImportBillDetailByImportBillId(Integer importBillDetailId);
	public void addImportBillDetail(ImportBillDetailEntity importBillDetail);

}
