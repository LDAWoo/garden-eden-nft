package com.example.gardenedennft.owner;

import com.example.gardenedennft.artist.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OwnerResponse extends JpaRepository<Owner, UUID> {

    @Transactional
    @Query("""
    SELECT o FROM Owner o WHERE o.artist.id = ?1 AND o.wallet_address = ?2 
    """)
    Optional<Owner> findOwnerByIdArtistAndWalletAddress(UUID id, String walletAddress);

    @Transactional
    @Query("""
    SELECT o FROM Owner o WHERE  o.wallet_address = ?1 
    """)
    Optional<Owner> findOwnerByWalletAddress(String walletAddress);

    @Transactional
    @Query("""
    SELECT o FROM Owner o WHERE o.artist.id = ?1 
    """)
    Optional<Owner> findOwnerByIdArtist(UUID id);

    @Query("""
    SELECT o FROM Owner o WHERE o.artist.id = ?1 
    """)
    Optional<List<Owner>> findAllOwnerByIdArtist(UUID id);

    @Query("""
    SELECT o FROM Owner o WHERE o.artist = ?1
    """)
    Optional<List<Owner>> findAllByArtist(Artist artist);

    @Transactional(readOnly = true)
    @Query("SELECT COUNT(o) > 0 FROM Owner o WHERE o.artist.id = ?1 AND o.wallet_address = ?2")
    Boolean existsOwnerByIdArtistAndWalletAddress(UUID id, String walletAddress);

    @Transactional(readOnly = true)
    @Query("SELECT COUNT(o) > 0 FROM Owner o WHERE o.wallet_address = ?1")
    Boolean existsOwnerByWalletAddress(String walletAddress);
}
