package ar.com.telecom.shiva.persistencia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ar.com.telecom.shiva.persistencia.modelo.test.TestTableModel;


public interface TestRepository extends JpaRepository<TestTableModel, Integer> {
	
	@Query("FROM TestTableModel")
	List<TestTableModel> buscarTodos();

	@Query("FROM TestTableModel where id = ?1")
	TestTableModel buscarId(int id);
	
	TestTableModel findByDescription(String description);
	
// 	 Example with positional params
//   @Modifying
//   @Transactional(readOnly=false)
//   @Query("update Product p set p.description = ?2 where p.productId = ?1")
//   Integer setNewDescriptionForProducto(String productId, String description);
// 
//   Example with named params
//   @Modifying
//   @Query("update Product p set p.description = :description where p.productId = :productId")
//   Integer setNewDescriptionForProduct(@Param("productId") String productId,
//      @Param("description") String description);
	
//	 @Query(
//	     "Select t FROM Todo t WHERE LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) " +
//	      "OR LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))"
//	    )
//	    public List<Todo> search(@Param("searchTerm") String searchTerm);
}
