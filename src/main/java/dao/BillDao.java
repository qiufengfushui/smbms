package dao;


import entity.Bill;
import org.apache.ibatis.annotations.Param;
import util.PageBean;

import java.util.List;

public interface BillDao {

	List<Bill> getAllBill();

    int findByBillCount(@Param("id") long id);

    int findByProNameCount(@Param("key") String key);

    int findByProIdCount(@Param("proId") int proId);

    int getBillCount(@Param("key") String key, @Param("proId") int proId, @Param("isPayment") String isPayment);

    List<Bill> getBill(@Param("pageBean") PageBean<Bill> pageBean, @Param("key") String key, @Param("proId") int proId, @Param("isPayment") String isPayment);

    Bill getBillSingle(@Param("id") long id);

    int delBill(@Param("id") long id);

    int addBill(@Param("bill") Bill bill);

    int updBill(@Param("bill")Bill bill);
}
