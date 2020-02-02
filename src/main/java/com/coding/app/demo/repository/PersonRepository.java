/**
 * 
 */
package com.coding.app.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.app.demo.entity.Person;

/**
 * @author Kathir
 *
 */

public interface PersonRepository extends JpaRepository<Person, Long> {

}
