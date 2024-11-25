package agrStore.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import agrStore.DAO.ImportBillDAO;
import agrStore.entity.ImportBillEntity;
import agrStore.service.ImportBillService;

@Transactional
@Service
public class ImportBillServiceImpl implements ImportBillService{
	
	@Autowired
	private ImportBillDAO importBillDAO;

	@Override
	public List<ImportBillEntity> getAllImportBill() {
		// TODO Auto-generated method stub
		return this.importBillDAO.getAllImportBill();
	}

	@Override
	public ImportBillEntity getImportBillEntityById(Integer id) {
		// TODO Auto-generated method stub
		return this.importBillDAO.getImportBillById(id);
	}

}
