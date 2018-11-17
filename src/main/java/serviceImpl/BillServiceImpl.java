package serviceImpl;

import dao.BillDao;
import entity.Bill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import service.BillService;
import util.PageBean;

import java.util.List;

@Service("billService")
public class BillServiceImpl implements BillService {

	@Autowired
	@Qualifier("billDao")
	private BillDao billDao;

	@Override
	public List<Bill> getAllBill() {
		// TODO Auto-generated method stub
		return billDao.getAllBill();
	}

	@Override
	public int findByProNameCount(String key) {
		return billDao.findByProNameCount(key);
	}

	@Override
	public int findByProIdCount(int proId) {
		return billDao.findByProIdCount(proId);
	}

	@Override
	public int getBillCount(String key, int proId, String isPayment) {
		return billDao.getBillCount(key,proId,isPayment);
	}

	@Override
	public PageBean<Bill> getBill(PageBean<Bill> pageBean, String key, int proId, String isPayment) {
		List<Bill> list = billDao.getBill(pageBean,key,proId,isPayment);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Bill getBillSingle(long id) {
		return billDao.getBillSingle(id);
	}

	@Override
	public int delBill(long id) {
		return billDao.delBill(id);
	}

	@Override
	public int addBill(Bill bill) {
		return billDao.addBill(bill);
	}

	@Override
	public int updBill(Bill bill) {
		return billDao.updBill(bill);
	}

}
