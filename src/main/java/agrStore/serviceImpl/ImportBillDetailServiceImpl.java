package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ImportBillDetailDAO;
import agrStore.entity.ImportBillDetailEntity;
import agrStore.service.ImportBillDetailService;

@Transactional
@Service
public class ImportBillDetailServiceImpl implements ImportBillDetailService{
	
	@Autowired
	private ImportBillDetailDAO importBillDetailDAO;

	@Override
	public List<ImportBillDetailEntity> getImportBillDetailByImportBillId(Integer importBillDetailId) {
		// TODO Auto-generated method stub
		return this.importBillDetailDAO.getImportBillDetailByImportBillId(importBillDetailId);
	}

	@Override
	public void addImportBillDetail(ImportBillDetailEntity importBillDetail) {
		// TODO Auto-generated method stub
		this.importBillDetailDAO.addImportBillDetail(importBillDetail);
	}

}
