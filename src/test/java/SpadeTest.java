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
        spade = new Spade(2);
        mockField = mock(IceBlock.class);
        mockPlayer = mock(Researcher.class);
    }


    @Test
    void Spade_Use_WorkAmountDecreaseCheckForPlayer() {
        //Arrange
        when(mockField.getLayerOfSnow()).thenReturn(2);
        when(mockPlayer.getField()).thenReturn(mockField);

        //Act
        spade.setHolder(mockPlayer);
        spade.Use(mockPlayer);

        //Assert
        verify(mockPlayer, times(1)).decreaseWorkUnits();
    }

    @Test
    void Spade_Use_SnowDecrease() {
        //Arrange
        when(mockField.getLayerOfSnow()).thenReturn(2);
        when(mockPlayer.getField()).thenReturn(mockField);

        //Act
        spade.setHolder(mockPlayer);
        spade.Use(mockPlayer);

        //Assert
        verify(mockField, times(1)).DecrLayerOfSnow(2);
    }

}
