package ar.com.telecom.shiva.test.dao;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ar.com.telecom.shiva.persistencia.modelo.test.TestTableModel;
import ar.com.telecom.shiva.persistencia.repository.TestRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles(profiles = "desa_local_ldap")
@ContextConfiguration(locations = { "classpath*:/spring-context.xml" })
public class RepositoryTest {

	@Autowired TestRepository testRepository;
	
	@Test
	public void test() {
		PageRequest request = new PageRequest(0, 5, Sort.Direction.DESC, "id");
		Page<TestTableModel> page = testRepository.findAll(request);
		List<TestTableModel> list = page.getContent();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDescription());
		}
	}
	
}
