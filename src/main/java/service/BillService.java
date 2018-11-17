package service;

import entity.Bill;
import util.PageBean;

import java.util.List;

public interface BillService {

	List<Bill> getAllBill();

    int findByProNameCount(String key);

    int findByProIdCount(int proId);

    int getBillCount(String key, int proId, String isPayment);

    PageBean<Bill> getBill(PageBean<Bill> pageBean, String key, int proId, String isPayment);

    Bill getBillSingle(long id);

    int delBill(long id);

    int addBill(Bill bill);

    int updBill(Bill bill);
}
