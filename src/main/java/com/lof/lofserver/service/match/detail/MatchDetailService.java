package com.lof.lofserver.service.match.detail;

import com.lof.lofserver.domain.game.GameEntity;
import com.lof.lofserver.domain.game.GameRepository;
import com.lof.lofserver.domain.game.sub.Opponent;
import com.lof.lofserver.domain.game.sub.Player;
import com.lof.lofserver.domain.game.sub.Team;
import com.lof.lofserver.domain.match.MatchEntity;
import com.lof.lofserver.domain.match.MatchRepository;
import com.lof.lofserver.domain.match.sub.Game;
import com.lof.lofserver.domain.team.TeamEntity;
import com.lof.lofserver.domain.team.TeamRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class MatchDetailService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;
    private final GameRepository gameRepository;
    private TeamVsTeamSetInfo setKDA(GameEntity gameEntity) {
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "KDA";
            Opponent blueTeam = null, redTeam = null;
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0).getTeam();
                redTeam = gameEntity.getTeams().get(1).getTeam();
            }
            else{
                blueTeam = gameEntity.getTeams().get(1).getTeam();
                redTeam = gameEntity.getTeams().get(0).getTeam();
            }
            Long blueKills = 0L, blueDeaths = 0L, blueAssists = 0L;
            Long redKills = 0L, redDeaths = 0L, redAssists = 0L;
            for(Player player :gameEntity.getPlayers()){
                if(player.getTeam().getId().equals(blueTeam.getId())){
                    blueKills += player.getKills();
                    blueDeaths += player.getDeaths();
                    blueAssists += player.getAssists();
                }
                else{
                    redKills += player.getKills();
                    redDeaths += player.getDeaths();
                    redAssists += player.getAssists();
                }
            }
            String blueKDA = blueKills + "/" + blueDeaths + "/" + blueAssists;
            String redKDA = redKills + "/" + redDeaths + "/" + redAssists;
            StringObject stringObject = new StringObject(text, blueKDA, redKDA);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "KDA";
            TeamEntity blueTeamEntity = null, redTeamEntity = null;
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeamEntity = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null);
                redTeamEntity = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null);
            }
            else{
                blueTeamEntity = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null);
                redTeamEntity = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null);
            }
            Long blueKills = Double.doubleToLongBits(blueTeamEntity.getStatus().getAverages().getKills());
            Long blueDeaths = Double.doubleToLongBits(blueTeamEntity.getStatus().getAverages().getDeaths());
            Long blueAssists = Double.doubleToLongBits(blueTeamEntity.getStatus().getAverages().getAssists());

            Long redKills = Double.doubleToLongBits(redTeamEntity.getStatus().getAverages().getKills());
            Long redDeaths = Double.doubleToLongBits(redTeamEntity.getStatus().getAverages().getDeaths());
            Long redAssists = Double.doubleToLongBits(redTeamEntity.getStatus().getAverages().getAssists());

            String blueKDA = blueKills + "/" + blueDeaths + "/" + blueAssists;
            String redKDA = redKills + "/" + redDeaths + "/" + redAssists;
            StringObject stringObject = new StringObject(text, blueKDA, redKDA);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }

    private TeamVsTeamSetInfo setGold(GameEntity gameEntity){
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "GOLD";
            String blueGold = "", redGold = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueGold = (float)Math.round((float)gameEntity.getTeams().get(0).getGold_earned() / 100) /10 + "k";
                redGold = (float)Math.round((float)gameEntity.getTeams().get(1).getGold_earned() / 100 ) /10+ "k";
            }
            else{
                blueGold = (float)Math.round((float)gameEntity.getTeams().get(1).getGold_earned() / 100) /10 + "k";
                redGold = (float)Math.round((float)gameEntity.getTeams().get(0).getGold_earned() / 100) /10+ "k";
            }
            StringObject stringObject = new StringObject(text, blueGold, redGold);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "GOLD";
            String blueGold = "", redGold = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                Double blueGoldEarned = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getGold_earned();
                Double redGoldEarned = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getGold_earned();
                blueGold = (float)Math.round(blueGoldEarned / 100) /10+ "k";
                redGold = (float)Math.round(redGoldEarned / 100) /10+ "k";
            }
            else{
                Double blueGoldEarned = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getGold_earned();
                Double redGoldEarned = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getGold_earned();
                blueGold = (float)Math.round(blueGoldEarned / 100) /10+ "k";
                redGold = (float)Math.round(redGoldEarned / 100) /10+ "k";
            }
            StringObject stringObject = new StringObject(text, blueGold, redGold);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }
    private TeamVsTeamSetInfo setTower(GameEntity gameEntity){
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "TOWERS";
            String blueTower = "", redTower = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTower = gameEntity.getTeams().get(0).getTower_kills() + "";
                redTower = gameEntity.getTeams().get(1).getTower_kills() + "";
            }
            else{
                blueTower = gameEntity.getTeams().get(1).getTower_kills() + "";
                redTower = gameEntity.getTeams().get(0).getTower_kills() + "";
            }
            StringObject stringObject = new StringObject(text, blueTower, redTower);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "TOWERS";
            String blueTower = "", redTower = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTower = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getTower_kills() + "";
                redTower = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getTower_kills() + "";
            }
            else{
                blueTower = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getTower_kills() + "";
                redTower = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getTower_kills() + "";
            }
            StringObject stringObject = new StringObject(text, blueTower, redTower);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }
    private List<String> addByCount(List<String> list, String url, Long count){
        for(int i = 0; i < count; i++)
            list.add(url);
        return list;
    }

    private TeamVsTeamSetInfo setDrakes(GameEntity gameEntity){
        DragonImgs dragonImgs = new DragonImgs();
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_IMAGE_VIEW";
            String text = "DRAKES";
            Team blueTeam = null, redTeam = null;
            List<String> blueDrakes = new ArrayList<>(), redDrakes = new ArrayList<>();
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }

            blueDrakes = addByCount(blueDrakes, dragonImgs.getHextech_drake(), blueTeam.getHextech_drake_kills());
            blueDrakes = addByCount(blueDrakes, dragonImgs.getCloud_drake(), blueTeam.getCloud_drake_kills());
            blueDrakes = addByCount(blueDrakes, dragonImgs.getInfernal_drake(), blueTeam.getInfernal_drake_kills());
            blueDrakes = addByCount(blueDrakes, dragonImgs.getMountain_drake(), blueTeam.getMountain_drake_kills());
            blueDrakes = addByCount(blueDrakes, dragonImgs.getOcean_drake(), blueTeam.getOcean_drake_kills());

            redDrakes = addByCount(redDrakes, dragonImgs.getHextech_drake(), redTeam.getHextech_drake_kills());
            redDrakes = addByCount(redDrakes, dragonImgs.getCloud_drake(), redTeam.getCloud_drake_kills());
            redDrakes = addByCount(redDrakes, dragonImgs.getInfernal_drake(), redTeam.getInfernal_drake_kills());
            redDrakes = addByCount(redDrakes, dragonImgs.getMountain_drake(), redTeam.getMountain_drake_kills());
            redDrakes = addByCount(redDrakes, dragonImgs.getOcean_drake(), redTeam.getOcean_drake_kills());

            ImgObject imgObject = new ImgObject(text, blueDrakes, redDrakes);
            return new TeamVsTeamSetInfo(viewType, null, imgObject);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "DRAKES";
            String blueDrakes = "", redDrakes = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueDrakes = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getDragon_kills() + "";
                redDrakes = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getDragon_kills() + "";
            }
            else{
                blueDrakes = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getDragon_kills() + "";
                redDrakes = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getDragon_kills() + "";
            }

            StringObject stringObject = new StringObject(text, blueDrakes, redDrakes);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }

    private TeamVsTeamSetInfo setHeralds(GameEntity gameEntity){
        DragonImgs dragonImgs = new DragonImgs();
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_IMAGE_VIEW";
            String text = "HERALDS";
            Team blueTeam = null, redTeam = null;
            List<String> blueHeralds = new ArrayList<>(), redHeralds = new ArrayList<>();
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }
            for(int i = 0; i < blueTeam.getHerald_kills(); i++)
                blueHeralds.add(dragonImgs.getHerald());
            for(int i = 0; i < redTeam.getHerald_kills(); i++)
                redHeralds.add(dragonImgs.getHerald());

            ImgObject imgObject = new ImgObject(text, blueHeralds, redHeralds);
            return new TeamVsTeamSetInfo(viewType, null, imgObject);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "HERALDS";
            String blueHeralds = "", redHeralds = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueHeralds = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getHerald_kill() + "";
                redHeralds = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getHerald_kill() + "";
            }
            else{
                blueHeralds = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getHerald_kill() + "";
                redHeralds = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getHerald_kill() + "";
            }

            StringObject stringObject = new StringObject(text, blueHeralds, redHeralds);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }

    private TeamVsTeamSetInfo setElders(GameEntity gameEntity){
        DragonImgs dragonImgs = new DragonImgs();
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_IMAGE_VIEW";
            String text = "ELDERS";
            Team blueTeam = null, redTeam = null;
            List<String> blueElders = new ArrayList<>(), redElders = new ArrayList<>();
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }
            for(int i = 0; i < blueTeam.getElder_drake_kills(); i++)
                blueElders.add(dragonImgs.getElder_drake());
            for(int i = 0; i < redTeam.getElder_drake_kills(); i++)
                redElders.add(dragonImgs.getElder_drake());

            ImgObject imgObject = new ImgObject(text, blueElders, redElders);
            return new TeamVsTeamSetInfo(viewType, null, imgObject);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "FIRST DRAKE";
            String blueElders = "", redElders = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueElders = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getRatios().getFirst_dragon() + "";
                redElders = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getRatios().getFirst_dragon() + "";
            }
            else{
                blueElders = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getRatios().getFirst_dragon() + "";
                redElders = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getRatios().getFirst_dragon() + "";
            }

            StringObject stringObject = new StringObject(text, blueElders, redElders);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }

    private TeamVsTeamSetInfo setBarons(GameEntity gameEntity){
        DragonImgs dragonImgs = new DragonImgs();
        if(gameEntity.getStatus().equals("finished")) {
            String viewType = "MATCH_INFO_IMAGE_VIEW";
            String text = "BARONS";
            Team blueTeam = null, redTeam = null;
            List<String> blueBarons = new ArrayList<>(), redBarons = new ArrayList<>();
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }
            for(int i = 0; i < blueTeam.getBaron_kills(); i++)
                blueBarons.add(dragonImgs.getBaron());
            for(int i = 0; i < redTeam.getBaron_kills(); i++)
                redBarons.add(dragonImgs.getBaron());

            ImgObject imgObject = new ImgObject(text, blueBarons, redBarons);
            return new TeamVsTeamSetInfo(viewType, null, imgObject);
        }
        else{
            String viewType = "MATCH_INFO_STRING_VIEW";
            String text = "BARONS";
            String blueBarons = "", redBarons = "";
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueBarons = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getBaron_kills() + "";
                redBarons = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getBaron_kills() + "";
            }
            else{
                blueBarons = teamRepository.findById(gameEntity.getTeams().get(1).getTeam().getId()).orElse(null).getStatus().getAverages().getBaron_kills() + "";
                redBarons = teamRepository.findById(gameEntity.getTeams().get(0).getTeam().getId()).orElse(null).getStatus().getAverages().getBaron_kills() + "";
            }

            StringObject stringObject = new StringObject(text, blueBarons, redBarons);
            return new TeamVsTeamSetInfo(viewType, stringObject, null);
        }
    }

    public TeamVsTeamRosterInfo setRoster(GameEntity gameEntity){
        if(gameEntity.getStatus().equals("finished")) {
            Team blueTeam = null, redTeam = null;
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }
            List<Roster> blueRoster = new ArrayList<>(), redRoster = new ArrayList<>();
            for(Player player : gameEntity.getPlayers()){
                String role = "https://d654rq93y7j8z.cloudfront.net/line/"+ player.getRole() + ".png";
                if(player.getTeam().getId().equals(blueTeam.getTeam().getId()))
                    blueRoster.add(new Roster(player.getPlayer_id(), player.getRole(), player.getPlayer().getName(), role, player.getPlayer().getImage_url()));
                else
                    redRoster.add(new Roster(player.getPlayer_id(), player.getRole(), player.getPlayer().getName(), role, player.getPlayer().getImage_url()));

            }
            return new TeamVsTeamRosterInfo(blueRoster, redRoster);
        }
        return null;
    }

    public TeamVsTeamMainInfo setScore(Map<Long, Long> score , GameEntity gameEntity){
        if(gameEntity.getStatus().equals("finished")) {
            if (!score.containsKey(gameEntity.getTeams().get(0).getTeam().getId())) {
                score.put(gameEntity.getTeams().get(0).getTeam().getId(), 0L);
                score.put(gameEntity.getTeams().get(1).getTeam().getId(), 0L);
            }
            score.put(gameEntity.getWinner().getId(), score.get(gameEntity.getWinner().getId()) + 1);

            Team blueTeam = null, redTeam = null;
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeam = gameEntity.getTeams().get(0);
                redTeam = gameEntity.getTeams().get(1);
            }
            else{
                blueTeam = gameEntity.getTeams().get(1);
                redTeam = gameEntity.getTeams().get(0);
            }
            Long blueScore = score.get(blueTeam.getTeam().getId());
            Long redScore = score.get(redTeam.getTeam().getId());
            LocalDateTime localDateTime = gameEntity.getBeginAt();
            String date = localDateTime.getYear() + "년 " + localDateTime.getMonthValue() + "월 " + localDateTime.getDayOfMonth() + "일";
            String time = localDateTime.getHour() + "시 " + localDateTime.getMinute() + "분";
            Boolean blueWin, redWin;
            if(gameEntity.getWinner().getId().equals(blueTeam.getTeam().getId())){
                blueWin = true;
                redWin = false;
            }else {
                blueWin = false;
                redWin = true;
            }

            return new TeamVsTeamMainInfo(
                    date,
                    time,
                    blueTeam.getTeam().getAcronym(),
                    redTeam.getTeam().getAcronym(),
                    blueTeam.getTeam().getId(),
                    redTeam.getTeam().getId(),
                    blueTeam.getTeam().getImage_url(),
                    redTeam.getTeam().getImage_url(),
                    blueScore,
                    redScore,blueWin,redWin);

        }
        return null;
    }
    public TeamVsTeamPrediction setPre(GameEntity gameEntity){
        Map<Long, Long> score = new HashMap<>();
        if(gameEntity.getStatus().equals("finished")) {
            Long blueTeamId, redTeamId;
            if(gameEntity.getTeams().get(0).getColor().equals("blue")){
                blueTeamId = gameEntity.getTeams().get(0).getTeam().getId();
                redTeamId = gameEntity.getTeams().get(1).getTeam().getId();
            }
            else{
                blueTeamId = gameEntity.getTeams().get(1).getTeam().getId();
                redTeamId = gameEntity.getTeams().get(0).getTeam().getId();
            }
            score.put(blueTeamId, 0L);
            score.put(redTeamId, 0L);
            List<MatchEntity> matchEntityList = matchRepository.findAllByTeamIds(blueTeamId, redTeamId);
            matchEntityList.forEach(matchEntity -> {
                score.put( matchEntity.getResults().get(0).getTeam_id(), score.get(matchEntity.getResults().get(0).getTeam_id()) + matchEntity.getResults().get(0).getScore());
                score.put( matchEntity.getResults().get(1).getTeam_id(), score.get(matchEntity.getResults().get(1).getTeam_id()) + matchEntity.getResults().get(1).getScore());
            });
            return new TeamVsTeamPrediction(score.get(blueTeamId), score.get(redTeamId));
        }
        return null;
    }

    public ResponseEntity<?> getTeamVsTeam(Long matchId){
        MatchEntity matchEntity = matchRepository.findById(matchId).orElse(null);
        if(matchEntity == null)
            return new ResponseEntity<>("해당 id 없음", HttpStatus.NOT_FOUND);

        List<TeamVsTeamDetails> teamVsTeamDetails = new ArrayList<>();
        Map<Long, Long> teamScore = new HashMap<>();
        for(Game game : matchEntity.getGames()){
            List<TeamVsTeamSetInfo>  teamVsTeamSetInfoList = new ArrayList<>();
            GameEntity matchDetailEntity = gameRepository.findById(game.getId()).orElse(null);
            teamVsTeamSetInfoList.add(setKDA(matchDetailEntity));
            teamVsTeamSetInfoList.add(setGold(matchDetailEntity));
            teamVsTeamSetInfoList.add(setTower(matchDetailEntity));
            teamVsTeamSetInfoList.add(setHeralds(matchDetailEntity));
            teamVsTeamSetInfoList.add(setDrakes(matchDetailEntity));
            teamVsTeamSetInfoList.add(setElders(matchDetailEntity));
            teamVsTeamSetInfoList.add(setBarons(matchDetailEntity));

            TeamVsTeamRosterInfo teamVsTeamRosterInfo = setRoster(matchDetailEntity);
            TeamVsTeamMainInfo teamVsTeamMainInfo = setScore(teamScore, matchDetailEntity);
            TeamVsTeamPrediction teamVsTeamPrediction = setPre(matchDetailEntity);
            teamVsTeamDetails.add(new TeamVsTeamDetails(teamVsTeamSetInfoList, teamVsTeamRosterInfo, teamVsTeamMainInfo, teamVsTeamPrediction));
        }
        return new ResponseEntity<>(new TeamVsTeam(teamVsTeamDetails), HttpStatus.OK);
    }
}
