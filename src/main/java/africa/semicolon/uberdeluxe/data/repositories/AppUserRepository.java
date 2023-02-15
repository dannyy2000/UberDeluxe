package africa.semicolon.uberdeluxe.data.repositories;

import africa.semicolon.uberdeluxe.data.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
}
