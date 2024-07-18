package BlackJackets.BlackJackets.service.impl;
import BlackJackets.BlackJackets.dto.VenueDto;
import BlackJackets.BlackJackets.entity.Venue;
import BlackJackets.BlackJackets.repository.VenueRepo;
import BlackJackets.BlackJackets.service.VenueService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VenueServiceImpl implements VenueService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private VenueRepo venueRepo;

    // Create Venue
    @Override
    public String createNewVenue(VenueDto venueDto) {
        Venue venue = this.modelMapper.map(venueDto, Venue.class);
        this.venueRepo.save(venue);
        return "Venue added successfully";
    }

    // Get Venue By Id
    @Override
    public VenueDto getVenueById(int venueId) {
       Venue venue = this.venueRepo.findById(venueId).orElseThrow();
        return this.modelMapper.map(venue,VenueDto.class);
    }

    // Get All Venues
    @Override
    public List<VenueDto> getAllVenues() {
        List<Venue> all = this.venueRepo.findAll();
        return all.stream().map(
                dto -> new VenueDto(dto.getVenueId(),
                        dto.getVenueName(),
                        dto.getVenueImageUrl(),
                        dto.getVenueLocation(),
                        dto.getVenueEmail(),
                        dto.getVenuePhone(),
                        dto.getVenueGenres(),
                        dto.getVenueAge())).collect(Collectors.toList());

    }

    // Update Venue
    @Override
    public VenueDto updateVenue(int venueId, VenueDto venueDto) {
        Venue venue = this.venueRepo.findById(venueId).orElseThrow();
        venue.setVenueName(venueDto.getVenueName());
        venue.setVenueImageUrl(venueDto.getVenueImageUrl());
        venue.setVenueLocation(venueDto.getVenueLocation());
        venue.setVenueEmail(venueDto.getVenueEmail());
        venue.setVenuePhone(venueDto.getVenuePhone());
        venue.setVenueGenres(venueDto.getVenueGenres());
        venue.setVenueAge(venueDto.getVenueAge());

        venueRepo.save(venue);
        return this.modelMapper.map(venue, VenueDto.class);
    }

    // Delete Venue
    @Override
    public void deleteVenue(int venueId) {
        this.venueRepo.deleteById(venueId);
    }
}
