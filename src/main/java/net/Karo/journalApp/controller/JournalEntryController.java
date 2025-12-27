package net.Karo.journalApp.controller;

import net.Karo.journalApp.Etity.JournalEntry;
import net.Karo.journalApp.Etity.User;
import net.Karo.journalApp.services.JournalEntryServices;
import net.Karo.journalApp.services.UserServices;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {


    @Autowired
    private JournalEntryServices journalEntryservice;
    @Autowired
    private UserServices userSErvices;

    @GetMapping ("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser (@PathVariable String userName){
        User user=userSErvices.findByUserName(userName);
        List<JournalEntry> all =user.getJournalEntries();
        if (all!=null && !all .isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping ("{userName}")
    public ResponseEntity createEntry(@RequestBody JournalEntry myEntry,@PathVariable String userName){
        String authUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if(!authUser.equals(userName)){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        journalEntryservice.saveEntry(myEntry,userName);
        return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId){
        Optional<JournalEntry>journalEntry= (journalEntryservice.findId(myId));
        if (journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{userName}/{myId}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        journalEntryservice.deleteByuser(myId,userName);

        return ResponseEntity.ok("Journal entry deleted successfully");

    }
    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry, @PathVariable String userName) {
        JournalEntry journalEntryold = journalEntryservice.findId(id).orElse(null);
        if (journalEntryold != null) {
            journalEntryold.setTitle(
                    newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                            ? newEntry.getTitle()
                            : journalEntryold.getTitle()
            );
            journalEntryold.setContent(
                    newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                            ? newEntry.getContent()
                            : journalEntryold.getContent()
            );

            journalEntryservice.saveEntry(journalEntryold);
            return new ResponseEntity<>(journalEntryold, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
