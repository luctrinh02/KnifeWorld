package com.luctt.KnifeWorld.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luctt.KnifeWorld.entities.Bill;
import com.luctt.KnifeWorld.entities.BillDetail;
import com.luctt.KnifeWorld.entities.BillDetailPK;
@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, BillDetailPK>{
	List<BillDetail> findByBill(Bill bill);
}
