package com.petclinic.spec

import com.petclinic.common.testgroups.Regression
import com.petclinic.common.testgroups.Smoke
import com.petclinic.dto.Pet
import com.petclinic.services.PetService
import org.junit.experimental.categories.Category

import static com.petclinic.databuilders.PetCreator.samplePetRequest

@Category(Regression)
class PetSpec extends BaseSpec {

    @Category(Smoke)
    def 'should return all pets'() {
        when: 'request for getting all pets is sent'
        Pet[] allPets = PetService.getAllPets()

        then: 'all pets are returned'
        allPets.size() >= 0
    }

    def 'should add a new pet'() {
        given: 'a new pet sample'
        int initialPetAmounts = PetService.getAllPets().size()
        Pet petRequest = samplePetRequest()

        when: 'request for adding pet is sent'
        Pet addedPet = PetService.addPet(petRequest)

        then: 'pet is created'
        addedPet.name == petRequest.name
        addedPet.owner.id == petRequest.owner.id

        and: 'pets amount increases'
        int actualPetsAmount = PetService.getAllPets().size()
        actualPetsAmount == initialPetAmounts + 1
    }
}
