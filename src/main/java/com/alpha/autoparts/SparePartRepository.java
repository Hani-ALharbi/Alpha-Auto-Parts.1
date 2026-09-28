package com.alpha.autoparts;

import org.springframework.data.jpa.repository.JpaRepository;

// هنا نربط الـ Interface بالكلاس SparePart ونخبره أن الـ ID نوعه Integer
public interface SparePartRepository extends JpaRepository<SparePart, Integer> {
    // لا تكتب شيء هنا، Spring Data JPA بيوفر لك كل عمليات الإضافة، التعديل، الحذف، والعرض جاهزة!
}