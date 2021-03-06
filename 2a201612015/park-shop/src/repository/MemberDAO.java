package repository;

import java.util.List;

import domain.Member;

public interface MemberDAO {
	// CRUD: basic method on DBMS query. => CREATE, SELECT, UPDATE, DELETE
	
	// INSERT (or CREATE)
	int create(Member m);
	
	// SELECT
	Member readList(String m);
	
	// UPDATE
	int update(Member m);
	
	// DELETE (or DROP table...)
	int delete(Member m);

}
