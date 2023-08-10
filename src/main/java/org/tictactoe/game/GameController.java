package org.tictactoe.game;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.tictactoe.entity.Game;
import org.tictactoe.entity.GameStatusEnum;
import org.tictactoe.entity.SymbolEnum;
import org.tictactoe.entity.User;
import org.tictactoe.util.NotFoundException;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;
    private final GameRepository gameRepository;
    private final GameModelFactory gameModelFactory;
    private GameVoter gameVoter = new GameVoter();//TODO создание бинов не очень понятно

    @GetMapping("/")
    public String index(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Game> gamesActive = gameRepository.findByCreatorOrSecondPlayerAndCreatedOrActiveStatus(user);
        List<Game> gamesCompleted = gameRepository.findByCreatorOrSecondPlayerAndWinOrStandoffStatus(user);

        model.addAttribute("games_active", gamesActive);
        model.addAttribute("games_completed", gamesCompleted);
        return "index";
    }

    @GetMapping("/game/create")
    public String create(Model model) {
        GameCreateFormRequest gameCreateFormRequest = new GameCreateFormRequest();

        model.addAttribute("game", gameCreateFormRequest);
        model.addAttribute("symbols", SymbolEnum.values());
        return "game/game_create_form";
    }

    @PostMapping("/game/create")
    public String create(@Valid @ModelAttribute(name = "game")
                         GameCreateFormRequest gameCreateFormRequest,
                         BindingResult bindingResult,
                         Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("game", gameCreateFormRequest);
            return "game/game_create_form";
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        gameCreateFormRequest.setUser(user);
        gameService.createGame(gameCreateFormRequest);
        return "redirect:/";
    }

    @GetMapping("/game/{id}/invite")
    public String inviteToGame(@PathVariable("id") long id, Model model) {
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found, id=" + id));
        if (!gameVoter.isGameStatusCreated(game)) {
            return "redirect:/";
        }
        ;
        model.addAttribute("game", game);
        return "game/game_invite";
    }

    @GetMapping("/game/connect")
    public String connectToGame(Model model) {
        GameConnectFormRequest gameConnectFormRequest = new GameConnectFormRequest();
        model.addAttribute("game", gameConnectFormRequest);

        return "game/game_connect";
    }

    @PostMapping("/game/connect")
    public String connectToGame(@Valid @ModelAttribute(name = "game")
                                GameConnectFormRequest gameConnectFormRequest,
                                BindingResult bindingResult,
                                Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("game", gameConnectFormRequest);
            return "game/game_connect";
        }

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        gameConnectFormRequest.setSecondPlayer(user);
        gameService.connectToGame(gameConnectFormRequest);

        return "redirect:/";
    }

    @GetMapping("/game/{id}")
    public String playGame(@PathVariable("id") long id, Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Game not found, id=" + id));

        GameModel gamePlayFormRequest = gameModelFactory.createGameModel(game, user);

        model.addAttribute("game", game);
        model.addAttribute("gamePlay", gamePlayFormRequest);
        return "game/game_play";
    }

    @PostMapping("/game/{id}/turn")
    public String playGame(@PathVariable("id") long id,
                           GameTurnDto gameTurnDto,
                           BindingResult bindingResult,
                           Model model) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = gameRepository.findById(id).orElseThrow(() -> new NotFoundException("Game not found, id=" + id));

        GameModel gameModel = gameModelFactory.createGameModel(game, user);
        gameModel.makeTurn(gameTurnDto.getCoordinateI(), gameTurnDto.getCoordinateJ());

        gameRepository.save(game);

        return "redirect:/game/" + game.getId();
    }

    @GetMapping("/game/{id}/delete")
    public String deleteGame(@PathVariable("id") long id) {

//        Optional<Game> optGame = gameRepository.findById(id);
//        Supplier<NotFoundException> exceptionalProducer = new Supplier<NotFoundException>() {
//            @Override
//            public NotFoundException get() {
//                return new NotFoundException("Game not found, id=" + id);
//            }
//        };
//        if(optGame.isEmpty()) {
//            var ex = exceptionalProducer.get();
//            throw ex;
//        } else {
//            Game game = optGame.get();
//        }

        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Game not found, id=" + id));
        gameRepository.delete(game);
        return "redirect:/";
    }


}
