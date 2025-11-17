package com.example.backend.repository;
import com.example.backend.model.Users;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    // Buscar por username directamente
    public Users findByUsername(String username);

    // Buscar si ya existe ese username (para validaciones)
    @Query("SELECT count(u.username) FROM Users u WHERE u.username = :username")
    public int buscarUsername(@Param("username") String username);

    // Insertar rol al usuario
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO roles (rol, user_id) VALUES (:rol, :user_id)", nativeQuery = true)
    public void insRol(@Param("rol") String authority, @Param("user_id") Long user_id);
}
