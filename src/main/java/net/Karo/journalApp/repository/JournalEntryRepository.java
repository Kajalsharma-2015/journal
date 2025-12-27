package net.Karo.journalApp.repository;

import net.Karo.journalApp.Etity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository <JournalEntry, ObjectId>{



}
