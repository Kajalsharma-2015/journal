package net.Karo.journalApp.repository;

import net.Karo.journalApp.Etity.JournalEntry;
import net.Karo.journalApp.Etity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository <User, ObjectId>{

      User findByUserName(String username);
}
