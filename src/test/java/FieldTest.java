import Field.Field;
import Field.Hole;
import Field.IceBlock;
import Player.Player;
import Player.Researcher;


import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FieldTest {

    private Field currentIceBlock;
    private Field targetHole;
    private Player mockPlayer;

    @BeforeEach
    void init() {
        currentIceBlock = new IceBlock();
        targetHole = new Hole();
        mockPlayer = spy(Researcher.class);
    }

    @Test
    void Field_PlayerStepsInWater() {
        //Arrange
        mockPlayer.setField(currentIceBlock);

        //Act
        mockPlayer.Step(targetHole);

        //Assert
        verify(mockPlayer, times(1)).setInWater(true);
    }

}
