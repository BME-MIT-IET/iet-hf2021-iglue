import Field.Field;
import Field.IceBlock;
import Item.Item;
import Item.Spade;
import Player.Researcher;
import Player.Player;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpadeTest {

    private Spade spade;
    private Field mockField;
    private Player mockPlayer;

    @BeforeEach
    void init() {
        spade = new Spade(1);
        mockField = mock(IceBlock.class);
        mockPlayer = mock(Researcher.class);
    }


    @Test
    //Játékos havat lapátol. A munkaegysége csökken.
    void Spade_Use_WorkAmountDecreaseCheckForPlayer() {
        //Arrange
        when(mockField.getLayerOfSnow()).thenReturn(2);
        when(mockPlayer.getField()).thenReturn(mockField);
        spade.setHolder(mockPlayer);

        //Act
        spade.Use(mockPlayer);

        //Assert
        verify(mockPlayer, times(1)).DecreaseWorkUnits();
    }

    @Test
    //Játékos havat lapátol. Az ásó 2 egység havat takarít el a mezőről
    void Spade_Use_SnowDecrease() {
        //Arrange
        when(mockField.getLayerOfSnow()).thenReturn(2);
        when(mockPlayer.getField()).thenReturn(mockField);
        spade.setHolder(mockPlayer);

        //Act
        spade.Use(mockPlayer);

        //Assert
        verify(mockField, times(1)).DecrLayerOfSnow(2);
    }

}
