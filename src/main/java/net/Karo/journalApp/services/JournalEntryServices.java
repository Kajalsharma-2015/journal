package net.Karo.journalApp.services;

import net.Karo.journalApp.Etity.JournalEntry;
import net.Karo.journalApp.Etity.User;
import net.Karo.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryServices {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserServices userServices; //userservice class ka object create kara hai

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user =userServices.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved=journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);

            userServices.saveEntry(user);
        } catch (RuntimeException e) {
            System.out.println(e);
            throw new RuntimeException("an error occurred while saving entry ",e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();

    }
    public Optional<JournalEntry> findId(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public void deleteById(ObjectId id, String userName){
         User user=userServices.findByUserName(userName);
         user.getJournalEntries().removeIf(x ->x.getId().equals(id));
         userServices.saveEntry(user);
        journalEntryRepository.deleteById(id);
    }

}
