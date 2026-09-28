package com.alpha.autoparts;

import org.springframework.data.jpa.repository.JpaRepository;

// هنا نربط الـ Interface بالكلاس Supplier ونخبره أن الـ ID نوعه Integer
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    // لا تكتب شيء هنا، هو جاهز بكل عمليات الإضافة والحذف!
}
